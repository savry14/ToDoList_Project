<script setup>
import { ref } from "vue";

const emit = defineEmits(["close", "add", "edit"]);
const props = defineProps(["task"]);
const taskTitle = ref(props.task ? props.task.title : "");

function handleAddTask() {
  if (!taskTitle.value.trim()) return;
  if (props.task) {
    emit("edit", {
      id: props.task.id,
      title: taskTitle.value,
    });
  } else {
    emit("add", taskTitle.value);
    taskTitle.value = "";
  }
}
</script>

<template>
  <div
    @click="emit('close')"
    class="fixed inset-0 flex items-center justify-center bg-black/40"
  >
    <div @click.stop class="w-full max-w-md rounded-xl bg-white p-6">
      <h2 class="text-2xl font-semibold text-gray-900">
        {{ props.task ? "Edit Task" : "Add New Task" }}
      </h2>

      <div class="mt-6">
        <label class="mb-2 bold text-xl font-medium text-gray-700">
          Task title
        </label>

        <input
          v-model="taskTitle"
          type="text"
          placeholder="Enter your task..."
          class="w-full rounded-lg border border-gray-300 px-4 py-3 outline-none focus:border-green-500"
        />
      </div>
      <div class="mt-6 flex justify-end gap-3">
        <button
          @click="emit('close')"
          class="rounded-lg border border-gray-300 px-4 py-2 text-gray-700 hover:bg-gray-100"
        >
          Cancel
        </button>
        <button
          @click="handleAddTask"
          class="rounded-lg bg-green-600 px-4 py-2 text-white hover:bg-green-700"
        >
          Add Task
        </button>
      </div>
    </div>
  </div>
</template>
