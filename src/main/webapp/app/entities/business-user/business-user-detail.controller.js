(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('BusinessUserDetailController', BusinessUserDetailController);

    BusinessUserDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BusinessUser', 'User', 'StoreGroup', 'Store'];

    function BusinessUserDetailController($scope, $rootScope, $stateParams, previousState, entity, BusinessUser, User, StoreGroup, Store) {
        var vm = this;

        vm.businessUser = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prototypeApp:businessUserUpdate', function(event, result) {
            vm.businessUser = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
