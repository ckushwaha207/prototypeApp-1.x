(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('OrderDetailController', OrderDetailController);

    OrderDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Order', 'CommerceItem', 'Payment'];

    function OrderDetailController($scope, $rootScope, $stateParams, previousState, entity, Order, CommerceItem, Payment) {
        var vm = this;

        vm.order = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prototypeApp:orderUpdate', function(event, result) {
            vm.order = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
