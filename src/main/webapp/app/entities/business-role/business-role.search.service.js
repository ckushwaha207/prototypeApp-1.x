(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('BusinessRoleSearch', BusinessRoleSearch);

    BusinessRoleSearch.$inject = ['$resource'];

    function BusinessRoleSearch($resource) {
        var resourceUrl =  'api/_search/business-roles/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
