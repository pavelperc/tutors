// how to create, sort, filter tables with vue js:
// http://www.developerdrive.com/2017/07/creating-a-data-table-in-vue-js/

var personApi = Vue.resource('/api/persons');


Vue.component('persons-list', {
    props: ['persons'],
    template:
        '<table id="firstTable" class="table table-striped table-bordered" style="font-size: 16px;"">\n' +
        '    <thead>\n' +
        '    <tr>\n' +
        '        <th>id</th>\n' +
        '        <th>login</th>\n' +
        '        <th>email</th>\n' +
        '        <th>fullName</th>\n' +
        '    </tr>\n' +
        '    </thead>\n' +
        '    <tbody>\n' +
        '    <tr v-for="row in persons">\n' +
        '        <td>{{row.id}}</td>\n' +
        '        <td>{{row.login}}</td>\n' +
        '        <td>{{row.email}}</td>\n' +
        '        <td>{{row.fullName}}</td>\n' +
        '    </tr>\n' +
        '    </tbody>\n' +
        '</table>',
    created: function () {
        personApi.get().then(result =>
            result.json().then(data =>
                data.forEach(person => this.persons.push(person))
            )
        )
    }
});


var app = new Vue({
    el: '#app',
    template: '<persons-list :persons="persons" />',
    data: {
        persons: []
    }
});