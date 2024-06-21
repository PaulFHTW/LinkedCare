<script>
import Table from '../components/table/TableElement.vue'

export default ({
  name: "Patienten",
  components: {
    Table
    },
  setup() {
  },
})
</script>


<template>
  <div class="patients">
    <Table 
        fetchLink = "http://172.29.16.61/patients"
        tableClasses = "text-start table-striped"
        v-bind:tableColumns = "
            [
                {
                    name: 'ID',
                    field: 'id',
                    path: 'resource.id',
                    align: 'text-start',
                    weight: 'fw-light'
                },
                {
                    name: 'Vorname',
                    field: 'vorname',
                    path: 'resource.name[0].given[0]',
                    align: 'text-start',
                    color: 'text-primary',
                    weight: 'fw-bold'
                },
                {
                    name: 'Nachname',
                    field: 'nachname',
                    path: 'resource.name[0].family',
                },
                {
                    name: 'Geschlecht',
                    field: 'geschlecht',
                    path: 'resource.gender',
                    align: 'text-center',
                    color: 'text-secondary',
                },
                {
                    name: 'Geburtsdatum',
                    field: 'geburtsdatum',
                    path: 'resource.birthDate',
                    color: 'text-secondary',
                },
                {
                    name: 'Telefonnummer',
                    field: 'telefonnummer',
                    path: 'resource.telecom[0].value',
                    color: 'text-secondary',
                }
            ]"
    />
  </div>
</template>
