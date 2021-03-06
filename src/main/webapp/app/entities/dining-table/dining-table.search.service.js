(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('DiningTableSearch', DiningTableSearch);

    DiningTableSearch.$inject = ['$resource'];

    function DiningTableSearch($resource) {
        var resourceUrl =  'api/_search/dining-tables/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
