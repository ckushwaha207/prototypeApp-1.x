(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('DiningTableDetailController', DiningTableDetailController);

    DiningTableDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'DiningTable', 'Store'];

    function DiningTableDetailController($scope, $rootScope, $stateParams, previousState, entity, DiningTable, Store) {
        var vm = this;

        vm.diningTable = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prototypeApp:diningTableUpdate', function(event, result) {
            vm.diningTable = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
