<template>
    <div class="accordion-item">
        <h2 class="accordion-header" :id="item._id">
            <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse" :data-bs-target="'#' + item._id + '-collapse'" aria-expanded="false" :aria-controls="item._id + '-collapse'">
                {{item.headline}}
            </button>
        </h2>
        <div :id="item._id + '-collapse'" class="accordion-collapse collapse" :aria-labelledby="item._id">
            <div class="accordion-body">
                <Table 
                    :fetchLink = "item.tableData.fetchLink"
                    :tableClasses = "item.tableData.tableClasses"
                    :tableColumns = "item.tableData.tableColumns"
                />
            </div>
        </div>
    </div>
</template>

<script>
import Table from '../table/TableElement.vue'

export default {
  name: 'AccordionItemTable',
  components: {
      Table,
  },
  props: {
    item: Object
  },
}
</script>