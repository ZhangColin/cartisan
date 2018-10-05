var menuItem = Vue.extend({
    name: 'menu-item',
    props: {item: {}},
    template: [
        '<li v-if="item.type===0" class="treeview">\n',
        '  <a href="#">\n',
        '    <i v-if="item.icon != null" :class="item.icon"></i> <span>{{item.name}}</span>\n',
        '    <span class="pull-right-container">\n',
        '      <i class="fa fa-angle-left pull-right"></i>\n',
        '    </span>\n',
        '  </a>\n',
        '  <ul class="treeview-menu">\n',
        '    <menu-item :item="item" v-for="item in item.list"></menu-item>',
        '  </ul>\n',
        '</li>',
        '<li v-else-if="item.type===1">\n',
        '  <a :href="item.url">\n',
        '    <i v-if="item.icon !=null " :class="item.icon"></i> <span>{{item.name}}</span>\n',
        '  </a>\n',
        '</li>\n'
    ].join('')
});

Vue.component('menuItem', menuItem);

var vm = new Vue({
    el: '#menus',
    data: {
        menuList: {}
    },
    methods:{
        getMenuList: function (event) {
            $.getJSON("/modules/sys/menu/nav?_"+$.now(), function (result) {
                vm.menuList = result.menuList;
            })
        }
    },
    created: function () {
        this.getMenuList();
    }
});