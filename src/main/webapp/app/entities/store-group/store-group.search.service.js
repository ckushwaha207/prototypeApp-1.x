(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('StoreGroupSearch', StoreGroupSearch);

    StoreGroupSearch.$inject = ['$resource'];

    function StoreGroupSearch($resource) {
        var resourceUrl =  'api/_search/store-groups/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
