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
      let cookie = document.cookie;
      const myHeaders = new Headers();
      myHeaders.append("Content-Type", "application/x-www-form-urlencoded");
      myHeaders.append("Authorization", `Bearer ${cookie}`);
      myHeaders.append("Access-Control-Allow-Origin", "*");
      //const urlencoded = new URLSearchParams();
      //urlencoded.append("test", "test");

      const requestOptions = {
        method: "GET",
        headers: myHeaders,
        //body: urlencoded,
        redirect: "follow"
      };

      fetch("http://172.29.16.62:8081/PoC", requestOptions)
        .then((response) => response.json())
        .then((result) => {this.carePlanData = result;})
        .catch((error) => console.error(error));
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
