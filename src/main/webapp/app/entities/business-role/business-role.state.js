(function() {
    'use strict';

    angular
        .module('prototypeApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('business-role', {
            parent: 'entity',
            url: '/business-role?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'prototypeApp.businessRole.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/business-role/business-roles.html',
                    controller: 'BusinessRoleController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessRole');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('business-role-detail', {
            parent: 'business-role',
            url: '/business-role/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'prototypeApp.businessRole.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/business-role/business-role-detail.html',
                    controller: 'BusinessRoleDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('businessRole');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'BusinessRole', function($stateParams, BusinessRole) {
                    return BusinessRole.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'business-role',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('business-role-detail.edit', {
            parent: 'business-role-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-role/business-role-dialog.html',
                    controller: 'BusinessRoleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BusinessRole', function(BusinessRole) {
                            return BusinessRole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('business-role.new', {
            parent: 'business-role',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-role/business-role-dialog.html',
                    controller: 'BusinessRoleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('business-role', null, { reload: 'business-role' });
                }, function() {
                    $state.go('business-role');
                });
            }]
        })
        .state('business-role.edit', {
            parent: 'business-role',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-role/business-role-dialog.html',
                    controller: 'BusinessRoleDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['BusinessRole', function(BusinessRole) {
                            return BusinessRole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('business-role', null, { reload: 'business-role' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('business-role.delete', {
            parent: 'business-role',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/business-role/business-role-delete-dialog.html',
                    controller: 'BusinessRoleDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['BusinessRole', function(BusinessRole) {
                            return BusinessRole.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('business-role', null, { reload: 'business-role' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
