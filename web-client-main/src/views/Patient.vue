<script>
import Accordion from '../components/accordion/Accordion.vue'


export default ({
  name: "Patient",
  components: {
      Accordion
    },
  setup() {
  },
})
</script>

<template>
  <main id="patient" class="text-start" >
    <section class="p-5 mb-4 bg-light rounded-3" >
        <div class="container-fluid">
            <h1 class="display-5 fw-bold">Patient Name</h1>
            <div>
                Info und so
            </div>
        </div>
    </section>

    <Accordion v-bind:AccordionItems="
        [
            {
                _id:        'Laufende-Rezepten',
                headline:   '📝 Laufende Rezepten',
                component:  'AccordionItemTable',
                tableData: {
                    fetchLink: 'http://linkedcare.ddns.net/fhir/Patient',
                    tableClasses: 'text-start table-striped',
                    tableColumns:
                        [
                            {
                                name: 'ID',
                                field: 'id',
                                path: '',
                                align: 'text-start',
                                weight: 'fw-light',
                                colSize: '',
                                color: 'text-primary'
                            },
                            {
                                name: 'Titel',
                                field: 'titel',
                                
                                align: 'text-start',
                                color: 'text-primary',
                                weight: 'fw-bold',

                                isLink: true,

                                titlePrefix: 'Go to activities of patient ',
                                titlePath: 'resource.subject.id',
                                titleSuffix: '',

                                hrefPrefix: 'http://localhost:8080/patient/',
                                hrefPath: 'resource.subject.id',
                                hrefSuffix: '',

                                textPrefix: '🗂️',

                            },
                            {
                                name: 'Datum',
                                field: 'datum',
                                path: '',
                                align: 'text-end',
                            },
                            {
                                name: 'Gesendet',
                                field: 'gesendet',
                                path: '',
                                align: 'text-end',
                                color: 'text-secondary',
                            },
                        ]
                }
            },
            {
                _id:        'Entlassungsbriefe',
                headline:   '📄 Entlassungsbriefe',
                component:  'AccordionItemTable',
                tableData: {
                    fetchLink: 'http://linkedcare.ddns.net/fhir/Patient',
                    tableClasses: 'text-start table-striped',
                    tableColumns:
                        [
                            {
                                name: 'ID',
                                field: 'id',
                                path: '',
                                align: 'text-start',
                                weight: 'fw-light'
                            },
                            {
                                name: 'Titel',
                                field: 'titel',
                                path: '',
                                align: 'text-start',
                                color: 'text-primary',
                                weight: 'fw-bold'
                            },
                            {
                                name: 'Datum',
                                field: 'datum',
                                path: '',
                                align: 'text-end'
                            },
                            {
                                name: 'Bearbeitet',
                                field: 'bearbeitet',
                                path: '',
                                align: 'text-end',
                                color: 'text-secondary',
                            },
                        ]
                }
            },
            {
                _id:        'Vergangene-Medikation',
                headline:   '💊 Vergangene Medikation',
                component:  'AccordionItemTable',
                tableData: {
                    fetchLink: 'http://linkedcare.ddns.net/fhir/Patient',
                    tableClasses: 'text-start table-striped',
                    tableColumns:
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
                        ]
                }
            }
        ]"
    />
    
  </main>
</template>
