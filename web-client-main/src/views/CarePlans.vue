<script>
//import Table from '../components/table/TableElement.vue'

export default ({
  name: "Care Plans",
  //components: {
  //  Table
  //  },
  setup(){},
  data(){
    return{
      carePlanData: {
        
      }
    };
  },
  methods: {
    getData: function(){
      const myHeaders = new Headers();
        myHeaders.append("Authorization", "Bearer Admin");
        myHeaders.append("Cookie", "JSESSIONID=C2A4366647A9DBCA91AF2A1236658F25");

        const requestOptions = {
          method: "GET",
          headers: myHeaders,
          redirect: "follow"
        };

        fetch("http://172.29.16.62:8080/PoC", requestOptions)
          .then(response => response.json())
          .then(data => this.carePlanData = data);
      },
    },
    mounted(){
      this.getData()
    }
});
</script>

<template>
  <pre>{{ carePlanData }}</pre>
  <div class="carePlans">
      <!--<Table 
        fetchLink = "http://localhost:8081/PoC"
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

                    hrefPrefix: 'http://localhost:8080/carePlans/',
                    hrefPath: 'resource.subject.id',
                    hrefSuffix: '',

                    textPrefix: '🗂️',
                },
            ]"
    />-->
  </div>

</template>
