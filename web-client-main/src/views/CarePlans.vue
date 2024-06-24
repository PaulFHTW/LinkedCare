<script>
import Table from '../components/table/TableElement.vue'

export default ({
  name: "Care Plans",
  components: {
    Table
    },
  setup(){},
  data(){
    return{
      carePlanData: String
    } 
  },
  methods: {
    getData(){
      const requestOptions = {
      method: 'GET',
      headers: { "Authorization":"Bearer Admin" },
      };
      fetch('http://localhost:8081/PoC', requestOptions)
          .then(response => {
            this.carePlanData = JSON.stringify(response.data, null, "\t");
            console.log(response.data);
            return response;
          });
    }
  }
})

</script>

<template>
  <div class="carePlans">
      <Table 
        fetchLink = "http://172.29.16.61/carePlans"
        tableClasses = "text-start table-striped"
        v-bind:tableColumns = "
            [
                {
                    name: 'ID',
                    field: 'id',
                    path: 'resource.id',
                    align: 'text-start',
                },
                {
                    name: 'Patient ID',
                    field: 'patient-id',
                    path: 'resource.subject.id',
                    align: 'text-start',
                    weight: 'fw-bold'
                },
                {
                    name: 'Status',
                    field: 'staus',
                    path: 'resource.status',
                    color: 'text-primary',
                },
                {
                    name: 'Kategorie',
                    field: 'kategorie',
                    path: 'resource.category[0].text',
                },
                {
                    name: 'Activities',
                    field: 'activities',
                  
                    align: 'text-center',
                    isLink: true,

                    titlePrefix: 'Go to activities of patient ',
                    titlePath: 'resource.subject.id',
                    titleSuffix: '',

                    hrefPrefix: 'http://localhost:8080/patient/',
                    hrefPath: 'resource.subject.id',
                    hrefSuffix: '',

                    textPrefix: '🗂️',
                },
            ]"
    />
  </div>
  <div class="hello">
      <textarea v-model="carePlanData" rows="20" cols="80"></textarea>
      <ul id="items">
        <li v-for="(item, index) in listData" :key="index">
          {{ `${item.text} [${item.id}]` }}
        </li>
      </ul>
    </div>
</template>
