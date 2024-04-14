<script>
import Table from '../components/table/TableElement.vue'

export default ({
  name: "Care Plans",
  components: {
    Table
    },
  setup() {
  },
})
</script>

<template>
  <div class="carePlans">
    <h1>This is a care plans page</h1>
    <Table 
        fetchLink = "http://linkedcare.ddns.net/fhir/careplan"
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
</template>
