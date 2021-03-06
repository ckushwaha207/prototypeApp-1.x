(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('OrderSearch', OrderSearch);

    OrderSearch.$inject = ['$resource'];

    function OrderSearch($resource) {
        var resourceUrl =  'api/_search/orders/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
