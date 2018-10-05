$(document).ready(function () {
    $('.sidebar-menu').tree();

    var dt = $('#userTable').dataTable({
        ajax: {
            url: '/modules/sys/user/list',
            type: 'GET'
        },
        columns: [{
            data: 'userId'
        },{
            data: 'username'
        },{
            data: 'userId'
        },{
            data: 'username'
        },{
            data: 'username'
        },{
            data: 'status'
        }, {
            data: 'username'
        }, {
            data: 'userId'
        }],

        // ajax: {
        //     url: '@Url.Action("GetOrders")',
        //     type: 'POST',
        //     data: function(d) {
        //         if (d) {
        //             $('#searchForm').serializeArray().map(function(x) { d[x.name] = x.value; });
        //         }
        //     }
        // },
        // createdRow: function (row, data, index) {
        //
        // },
        // fnDrawCallback: function() {
        //
        //
        // },

    }).api();
});
