<script setup>
import TaskItem from "./TaskItem.vue";

const tasks = [
  {
    id: 1,
    title: "Finish homepage UI",
    completed: false,
    date: "Today",
  },
  {
    id: 2,
    title: "Study JavaScript",
    completed: true,
    date: "Yesterday",
  },
];
</script>

<template>
  <div>
    <div class="rounded-xl bg-white p-6">
      <TaskItem v-for="task in tasks" :key="task.id" :task="task" />
    </div>
  </div>
</template>
