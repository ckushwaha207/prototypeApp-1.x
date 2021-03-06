(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('PaymentDetailController', PaymentDetailController);

    PaymentDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Payment', 'TransactionStatus', 'Order'];

    function PaymentDetailController($scope, $rootScope, $stateParams, previousState, entity, Payment, TransactionStatus, Order) {
        var vm = this;

        vm.payment = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('prototypeApp:paymentUpdate', function(event, result) {
            vm.payment = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
