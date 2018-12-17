// how to create, sort, filter tables with vue js:
// http://www.developerdrive.com/2017/07/creating-a-data-table-in-vue-js/
function getStudents()
{
var personApi = Vue.resource('/api/persons');


Vue.component('persons-list', {
    props: ['persons'],
    template:
        '<table id="firstTable" class="table table-striped table-bordered" style="font-size: 16px; "">\n' +
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
}

function getSubjects()
{
/*var personApi = Vue.resource('/api/subjects');


Vue.component('subjects-list', {
    props: ['subjects'],
    template:
        '<table id="firstTable" class="table table-striped table-bordered" style="font-size: 16px; "">\n' +
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
});*/
  var subjectsApi = Vue.resource('/api/subjects');
  table = document.getElementById("table");

  subjectsApi.get().then(result =>
              result.json().then(data =>
                  data.forEach(subject => {
                    var newRow = table.insertRow(table.length);
                    var cellFirst = newRow.insertCell(0)
                    cellFirst.innerHTML = subject.id;

                    var cellSecond = newRow.insertCell(1)
                    cellSecond.innerHTML = subject.name;
                  })
              )
          )
 /* var array = [["A1","B1","C1"],
                         ["A2","B2","C2"],
                         ["A3","B3","C3"],
                         ["A4","B4","C4"],
                         ["A5","B5","C5"],
                         ["A1","B1","C1"],
                         ["A2","B2","C2"],
                         ["A3","B3","C3"],
                         ["A4","B4","C4"],
                         ["A5","B5","C5"]],


           for(var i = 0; i < array.length; i++)
                   {
                       // create a new row
                       var newRow = table.insertRow(table.length);
                       for(var j = 0; j < array[i].length; j++)
                       {
                           // create a new cell
                           var cell = newRow.insertCell(j);

                           // add value to the cell
                           cell.innerHTML = array[i][j];
                       }
                   }*/

}