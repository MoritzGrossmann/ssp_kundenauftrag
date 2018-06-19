function CustomerViewModel(customer) {
    var self = this;

    self.id = ko.observable(customer.id);
    self.name = ko.observable(customer.firstname + " " + customer.lastname);
}

function ProductionOrderViewModel(id, onDelete) {
    var self = this;
    self.id = ko.observable(id);
    self.delete = function() {
        onDelete(self);
    };
}

function OrderViewModel(id, baseUrl) {

    var self = this;

    self.id = ko.observable(id);

    self.dateTime = ko.observable("");

    self.customer = ko.observable();

    self.productionOrders = ko.observableArray([]);

    self.deleteProductionOrder = function(productionOrder) {
        $.ajax({
            method: "DELETE",
            url: baseUrl + "/api/order/" + self.id() + "/productionOrder/" + productionOrder.id()
        }).done(function(data) {
            self.productionOrders.remove(productionOrder);
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

                order.productionOrders.forEach(function(prodOrder) {
                    prodOrders[prodOrders.length] = new ProductionOrderViewModel(prodOrder.id, self.deleteProductionOrder);
                });

                self.productionOrders(prodOrders);

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

    self.addOrder = function() {
        $.ajax({
            method: "POST",
            url: baseUrl + "/api/order/" + self.id()
        }).done(function (productionOrder) {
            self.productionOrders.push(new ProductionOrderViewModel(productionOrder.id, self.deleteProductionOrder));
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