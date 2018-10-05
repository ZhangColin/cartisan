var Menu = {
    id: "menuTable",
    table: null,
    layerIndex: -1
};

Menu.initColumn = function () {
    var columns = [
        {field: 'selectItem', radio: true},
        {title: '菜单ID', field: 'menuId', visible: false, align: 'center', valign: 'middle', width: '80px'},
        {title: '菜单名称', field: 'name', align: 'center', valign: 'middle', sortable: true, width: '180px'},
        {title: '上级菜单', field: 'parentName', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '图标', field: 'icon', align: 'center', valign: 'middle', sortable: true, width: '80px', formatter: function(item, index){
            return item.icon == null? '': '<i class="'+item.icon+' fa-lg"></i>';
        }},
        {title: '类型', field: 'type', align: 'center', valign: 'middle', sortable: true, width: '100px', formatter: function(item, index){
            if (item.type === 0) {
                return '<span class="label label-primary">目录</span>';
            }
            if (item.type === 1) {
                return '<span class="label label-success">菜单</span>';
            }
            if (item.type === 2) {
                return '<span class="label label-warning">按钮</span>';
            }
        }},
        {title: '排序号', field: 'orderNum', align: 'center', valign: 'middle', sortable: true, width: '100px'},
        {title: '菜单URL', field: 'url', align: 'center', valign: 'middle', sortable: true, width: '160px'},
        {title: '授权标识', field: 'perms', align: 'center', valign: 'middle', sortable: true}
    ];

    return columns;
};

function getMenuId() {
    var selected = $('#menuTable').bootstrapTreeTable('getSelections');
    if (selected.length == 0) {
        alert('请选择一条记录。');
        return null;
    }
    else {
        return selected[0].id;
    }
};

$(function () {
    var columns = Menu.initColumn();
    var table = new TreeTable(Menu.id, '/modules/sys/menu/list', columns);
    table.setExpandColumn(2);
    table.setIdField('menuId');
    table.setCodeField('menuId');
    table.setParentCodeField('parentId');
    table.setExpandAll(false);
    table.init();
    Menu.table = table;
});