(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('CommerceItemSearch', CommerceItemSearch);

    CommerceItemSearch.$inject = ['$resource'];

    function CommerceItemSearch($resource) {
        var resourceUrl =  'api/_search/commerce-items/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
