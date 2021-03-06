(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .factory('MenuCategorySearch', MenuCategorySearch);

    MenuCategorySearch.$inject = ['$resource'];

    function MenuCategorySearch($resource) {
        var resourceUrl =  'api/_search/menu-categories/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true}
        });
    }
})();
