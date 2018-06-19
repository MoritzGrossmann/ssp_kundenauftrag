function OrderViewModel(id, baseUrl) {

    var self = this;

    self.id = ko.observable(id)

    self.dateTime = ko.observable("");

    self.customer = ko.observable("");

    self.productionOrders = ko.observableArray([]);

    self.getData = function () {
        $.ajax({
            method: "GET",
            url: baseUrl + "/api/order/" + self.id()
        }).done(function (order) {
            if (order) {
                self.dateTime(order.dateTime);

                var prodOrders = [];

                order.productionOrders.forEach(function(prodOrder) {
                    prodOrders[prodOrders.length] = prodOrder.id;
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
            self.customer(customer);
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