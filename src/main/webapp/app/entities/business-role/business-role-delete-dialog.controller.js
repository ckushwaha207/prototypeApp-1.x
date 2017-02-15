(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('BusinessRoleDeleteController',BusinessRoleDeleteController);

    BusinessRoleDeleteController.$inject = ['$uibModalInstance', 'entity', 'BusinessRole'];

    function BusinessRoleDeleteController($uibModalInstance, entity, BusinessRole) {
        var vm = this;

        vm.businessRole = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            BusinessRole.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
