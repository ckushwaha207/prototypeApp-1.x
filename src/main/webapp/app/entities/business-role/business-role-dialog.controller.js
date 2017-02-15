(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .controller('BusinessRoleDialogController', BusinessRoleDialogController);

    BusinessRoleDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'BusinessRole'];

    function BusinessRoleDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, BusinessRole) {
        var vm = this;

        vm.businessRole = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.businessRole.id !== null) {
                BusinessRole.update(vm.businessRole, onSaveSuccess, onSaveError);
            } else {
                BusinessRole.save(vm.businessRole, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('prototypeApp:businessRoleUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
