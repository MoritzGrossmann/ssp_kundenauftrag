function CustomerViewModel(customer) {
    var self = this;

    self.id = ko.observable(customer.id);
    self.name = ko.observable(customer.firstname + " " + customer.lastname);
}

function OrderItemViewModel(id, productId, count, onDelete) {
    var self = this;
    self.id = ko.observable(id);
    self.productId = ko.observable(productId);
    self.count = ko.observable(count);

    self.delete = function() {
        onDelete(self);
    };
}

function OrderViewModel(id, baseUrl) {

    var self = this;

    self.id = ko.observable(id);

    self.dateTime = ko.observable("");

    self.customer = ko.observable();

    self.orderItems = ko.observableArray([]);

    self.count = ko.observable(0);

    self.productId = ko.observable(0);

    self.deleteOrderItem = function(orderItem) {
        $.ajax({
            method: "DELETE",
            url: baseUrl + "/api/order/" + self.id() + "/items/" + orderItem.id()
        }).done(function(data) {
            self.orderItems.remove(orderItem);
        });
    };

    self.getData = function () {
        $.ajax({
            method: "GET",
            url: baseUrl + "/api/order/" + self.id()
        }).done(function (order) {
            if (order) {
                self.dateTime(order.dateTime);

                var prodOrders = [];

                order.orderItems.forEach(function(orderItem) {
                    prodOrders[prodOrders.length] = new OrderItemViewModel(orderItem.id, orderItem.productId, orderItem.count, self.deleteOrderItem);
                });

                self.orderItems(prodOrders);

                self.getCustomer();
            }
        });
    };

    self.getCustomer = function() {
        $.ajax({
            method: "GET",
            url: baseUrl + "/api/order/" + self.id() + "/customer"
        }).done(function (customer) {
            self.customer(new CustomerViewModel(customer));
        });
    };

    self.addOrderItem = function() {

        if (self.productId() < 1 || self.count() < 1) {
            alert("prodct and count must be positive");
            return;
        }

        var data = {
            productId: self.productId(),
            count: self.count()
        };

        $.ajax({
            method: "POST",
            url: baseUrl + "/api/order/" + self.id(),
            data: JSON.stringify(data),
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        }).done(function (orderItem) {
            self.orderItems.push(new OrderItemViewModel(orderItem.id, orderItem.productId, orderItem.count, self.deleteOrderItem));
            self.productId(0);
            self.count(0);
        });
    };

    self.getData();
}

$(function() {
    var baseUrl = document.getElementById("baseUrl").firstChild.nodeValue;
    var orderId = document.getElementById("orderId").firstChild.nodeValue;
    var viewModel = new OrderViewModel(orderId, baseUrl);
    ko.applyBindings(viewModel, document.getElementById("order"));
});