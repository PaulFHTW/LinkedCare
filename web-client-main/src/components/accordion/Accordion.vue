<template>
    <section class="accordion">        
        <template v-for="item in AccordionItems" :key="item._uid">
            <component :is="item.component" :item="item" />
        </template>
    </section>
</template>

<script>
import AccordionItemTable from './AccordionItemTable'

export default {
  name: 'Accordion',
  components: {
    AccordionItemTable
  },
  props: {
        AccordionItems: { type: Object, required: true },
    },
}
</script>