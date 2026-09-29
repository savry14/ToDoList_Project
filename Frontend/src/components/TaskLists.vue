<script setup>
import TaskItem from "./TaskItem.vue";
defineProps(["tasks", "totalTasks"]);
defineEmits(["delete"]);
</script>

<template>
  <div>
    <div class="mx-auto max-w-7xl rounded-xl bg-white p-6">
      <p class="mb-4 text-sm text-gray-500">
        {{ tasks.length }} {{ tasks.length === 1 ? "Task" : "Tasks" }}
      </p>
      <TaskItem
        v-for="task in tasks"
        :key="task.id"
        :task="task"
        @delete="$emit('delete', $event)"
        @edit="$emit('edit', $event)"
      />
      <p v-if="tasks.length === 0" class="py-8 text-center text-gray-500">
        {{ totalTasks === 0 ? "No tasks yet." : "No matching tasks found." }}
      </p>
    </div>
  </div>
</template>
