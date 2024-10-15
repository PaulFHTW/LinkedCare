<script>

export default {
  props: {
    link: { type: String, required: true },
    displayName: { type: String, required: true },
  },
  setup() {},
};
</script>

<template>
    <router-link :to="link">
      {{ displayName }}
    </router-link>
</template>
