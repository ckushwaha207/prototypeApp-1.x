(function() {
    'use strict';
    angular
        .module('prototypeApp')
        .factory('BusinessRole', BusinessRole);

    BusinessRole.$inject = ['$resource'];

    function BusinessRole ($resource) {
        var resourceUrl =  'api/business-roles/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
