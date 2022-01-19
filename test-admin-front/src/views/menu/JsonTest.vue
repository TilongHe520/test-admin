<template>
  <div class="test-json-viewer" style="text-align:left">
    <json-viewer
      :value="jsonData"
      :expand-depth="5"
      copyable
      boxed
      sort
    ></json-viewer>
  </div>
</template>

<script>
export default {
  data() {
    return {
      jsonData: [
        {
          AGE: 22,
          BIRTHDAY: "1999-12-26",
          GENDER: 1
        },
        {
          AGE: 21,
          BIRTHDAY: "2000-12-6",
          GENDER: 2,
          Hoads:["1","hello","world"]
        }
      ]
    };
  }
};
</script>

