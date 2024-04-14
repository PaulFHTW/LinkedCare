<template>
<table class="table" v-bind:class="tableContent.settings.tableClasses">
    <thead>
        <tr>
            <th v-for="column in tableContent.settings.tableColumns" :key="column.field" scope="col" v-bind:class="[column.align, column.colSize]">{{column.name}}</th>
        </tr>
    </thead>
        <tbody v-if="tableContent.loaded">
            <tr v-for="row in tableContent.result" :key="row.id">
            
                <template v-for="(element,index) in row" :key="JSON.stringify(element)">
                    <th v-if="index == 0" scope="row" v-bind:class="[tableContent.settings.tableColumns[index].align, tableContent.settings.tableColumns[index].color, tableContent.settings.tableColumns[index].weight, tableContent.settings.tableColumns[index].colSize]">{{element}}</th>
                    <td v-if="index !=0" :key="JSON.stringify(element)" v-bind:class="[tableContent.settings.tableColumns[index].align, tableContent.settings.tableColumns[index].color, tableContent.settings.tableColumns[index].weight, tableContent.settings.tableColumns[index].colSize]">
                        <template v-if="!isLink(tableContent.settings.tableColumns[index])">
                            {{element}}
                        </template>
                        <template v-else>
                            <a v-bind:href="tableContent.settings.tableColumns[index].hrefPrefix + element.href + tableContent.settings.tableColumns[index].hrefSuffix" v-bind:title="tableContent.settings.tableColumns[index].titlePrefix + element.title + tableContent.settings.tableColumns[index].titleSuffix">{{tableContent.settings.tableColumns[index].textPrefix}}{{element.text}}{{tableContent.settings.tableColumns[index].textSuffix}}</a>
                        </template>
                    </td>
                </template>

            </tr>
    </tbody>
</table>
</template>

<script>
export default {
    name: 'TableElement',
    props: {
        fetchLink: { type: String, required: true },
        tableColumns: {type: Object, required: true},
        tableClasses: { type: String, required: true },
    },
    data() {
        // Api Specification
        // https://gitlab.com/inno24/web-client/-/wikis/Components/Table
        return {
            tableContent: {
                loaded: false,
                fetchedData: {},
                result: [],
                settings: {
                    tableColumns: this.tableColumns,
                    tableClasses: this.tableClasses,
                }
                
            }
        }
    },
    methods: {
        // Check if the given field is a string or a link object
        isLink(element) {
            if (element.isLink){
                return true;
            }
            return false;
        },

        getList(){
            const thisComponent = this;
            fetch(this.fetchLink)
                .then(function(response) {
                    return response.json();
                })
                .then(function(data) {
                    thisComponent.tableContent.fetchedData = data;
                    var fetchedData = thisComponent.tableContent.fetchedData;
                    for (var index in fetchedData.entry){
                        
                        var row = [];
                        for (var column of thisComponent.tableContent.settings.tableColumns){
                            if(!column.isLink){
                                row.push( thisComponent.getInObject( fetchedData.entry[index], column.path));
                            } else {

                                var titleValue = null;
                                var hrefValue = null;
                                var textValue = null;

                                if(typeof column.titlePath !== 'undefined'){
                                    titleValue = thisComponent.getInObject( fetchedData.entry[index], column.titlePath)
                                }
                                if(typeof column.hrefPath !== 'undefined'){
                                    hrefValue = thisComponent.getInObject( fetchedData.entry[index], column.hrefPath)
                                }
                                if(typeof column.textPath !== 'undefined'){
                                    textValue = thisComponent.getInObject( fetchedData.entry[index], column.textPath)
                                }

                                var link = {
                                    title: titleValue,
                                    href: hrefValue,
                                    text: textValue,
                                }
                                row.push(link);
                            }
                            
                        }
                        
                        thisComponent.tableContent.result.push(row);
                    }
                    
                    thisComponent.tableContent.loaded = true;
                    
                })
                .catch(function(error) {
                    console.error(error);
                });
            
        },

        getInObject(obj, path, def) {
            /**
            * If the path is a string, convert it to an array
            * @param  {String|Array} path The path
            * @return {Array}             The path array
            */
            var stringToPath = function (path) {
                // If the path isn't a string, return it
                if (typeof path !== 'string'){
                    return path;
                }

                if (path.startsWith("_")){
                    def = path.substring(1)
                    return def;
                } 
                // Create new array
                var output = [];

                // Split to an array with dot notation
                path.split('.').forEach(function (item) {

                    // Split to an array with bracket notation
                    item.split(/\[([^}]+)\]/g).forEach(function (key) {

                        // Push to the new array
                        if (key.length > 0) {
                            output.push(key);
                        }

                    });

                });

                return output;
            };

            // Get the path as an array
            path = stringToPath(path);

            // Cache the current object
            var current = obj;

            // For each item in the path, dig into the object
            for (var i = 0; i < path.length; i++) {

                // If the item isn't found, return the default (or null)
                if (!current[path[i]]) return def;

                // Otherwise, update the current  value
                current = current[path[i]];

            }

            return current;

        },

    },
    beforeMount(){
        console.log(this.tableContent)
        this.getList()
    },
    mounted() {
        
    },
}
</script>

<style scoped></style>