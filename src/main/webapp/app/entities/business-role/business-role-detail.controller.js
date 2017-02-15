(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('BusinessRoleDetailController', BusinessRoleDetailController);

    BusinessRoleDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'BusinessRole'];

    function BusinessRoleDetailController($scope, $rootScope, $stateParams, previousState, entity, BusinessRole) {
        var vm = this;

        vm.businessRole = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prototypeApp:businessRoleUpdate', function(event, result) {
            vm.businessRole = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
