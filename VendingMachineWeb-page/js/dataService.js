function DataService() {
    var self = this;
    self.getAllItems = function (callback, errorCallback) {
        $.ajax({
            url: "http://tsg-vending.herokuapp.com/items",
            method: "GET",
            headers: {
                "accept": "application/json"
            },
            success: callback,
            error: errorCallback
        });
    };

    //	http://tsg-vending.herokuapp.com /money/{amount}/item/{id}
    self.getItemByID = function (itemID, amount, callback, errorCallback) {
        $.ajax({
            url: "http://tsg-vending.herokuapp.com/money/" + amount + "/item/" + itemID,
            method: "POST",
            headers: {
                "accept": "application/json"
            },
            success: callback,
            error: errorCallback
        });
    }
}