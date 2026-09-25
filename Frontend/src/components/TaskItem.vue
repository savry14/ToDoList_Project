<script setup>
defineProps(["task"]);
</script>

<template>
  <div
    class="p-4 mb-3 border rounded-lg border-gray-200 flex justify-between items-center"
  >
    <div class="flex items-center gap-3">
      <input v-model="task.completed"
       type="checkbox" name="" id="" class="h-5 w-5" />
      <p >{{task.title}}</p>
    </div>
    <p class="text-sm text-gray-500">{{task.date}}</p>
  </div>
</template>
