(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('BusinessUserSearch', BusinessUserSearch);

    BusinessUserSearch.$inject = ['$resource'];

    function BusinessUserSearch($resource) {
        var resourceUrl =  'api/_search/business-users/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
