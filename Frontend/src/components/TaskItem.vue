<script setup>
defineProps(["task"]);

const emit = defineEmits(["delete"]);
</script>

<template>
  <div
    class="px-4 py-3 mb-3 border rounded-lg border-gray-200 flex justify-between items-center"
  >
    <div class="flex min-w-0 items-start gap-3">
      <input
        v-model="task.completed"
        type="checkbox"
        name=""
        id=""
        class="mt-0.5 h-5 w-5"
      />
      <div class="min-w-0">
        <p
          :class="task.completed ? 'text-gray-400' : 'text-gray-900'"
          class="font-semibold"
        >
          {{ task.title }}
        </p>

        <p class="mt-1 text-xs text-gray-400">
          {{ task.date }}
        </p>
      </div>
    </div>
    <div class="flex items-center gap-3">
      <button  @click="emit('edit', task)"
        class="rounded-md border border-green-500 px-3 py-1.5 text-sm font-medium text-green-600 hover:bg-green-50"
      >
        Edit
      </button>

      <button
        @click="
          confirm('Are you sure you want to delete this task?') &&
          emit('delete', task.id)
        "
        class="rounded-md border border-red-400 px-3 py-1.5 text-sm font-medium text-red-500 hover:bg-red-50"
      >
        Delete
      </button>
    </div>
  </div>
</template>
