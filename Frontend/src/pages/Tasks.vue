<script setup>
import TaskLists from "../components/TaskLists.vue";
import AddTask from "../components/AddTask.vue";
import { Search } from "lucide-vue-next";
import { ref, computed, onMounted } from "vue";
import {
  getTasks,
  createTask,
  updateTask,
  deleteTask as deleteTaskApi,
} from "../api/ApiTask.js";

const activeFilter = ref("all");
const isSortOpen = ref(false);
const sortOrder = ref("newest");
const isAddTaskOpen = ref(false);
const searchQuery = ref("");
const editingTask = ref(null);

const tasks = ref([]);

onMounted(() => {
  getTasks().then((response) => {
    tasks.value = response.data;
  });
});

async function addTask(title) {
  const response = await createTask({
    title: title,
  });

  tasks.value.push(response.data);

  isAddTaskOpen.value = false;
}

function openEditTask(task) {
  editingTask.value = task;
  isAddTaskOpen.value = true;
}
async function editTask(task) {
  const response = await updateTask(task.id, {
    title: task.title,
  });

  const index = tasks.value.findIndex((item) => item.id === task.id);

  tasks.value[index] = response.data;

  isAddTaskOpen.value = false;
  editingTask.value = null;
}
async function updateTaskStatus(task) {
  const response = await updateTask(task.id, {
    title: task.title,
    completed: task.completed,
  });

  const index = tasks.value.findIndex((item) => item.id === task.id);

  tasks.value[index] = response.data;
}

async function deleteTask(id) {
  await await deleteTaskApi(id);

  tasks.value = tasks.value.filter((task) => task.id !== id);
}

const filteredTasks = computed(() => {
  const result = tasks.value.filter((task) => {
    const matcheSearch = task.title
      .toLowerCase()
      .includes(searchQuery.value.toLowerCase());

    const matchesFilter =
      activeFilter.value === "all" ||
      (activeFilter.value === "active" && !task.completed) ||
      (activeFilter.value === "completed" && task.completed);
    return matcheSearch && matchesFilter;
  });

  if (sortOrder.value === "newest") {
    return [...result].sort((a, b) => b.createdAt - a.createdAt);
  }
  return [...result].sort((a, b) => a.createdAt - b.createdAt);
});
</script>

<template>
  <div class="min-h-screen w-full bg-gray-50 px-4 py-8">
    <div class="mx-auto max-w-5xl rounded-2xl bg-white p-2 shadow-sm">
      <!-- Header -->
      <section class="px-4 pt-5 pb-4 sm:px-6 sm:pt-6">
        <div
          class="mx-auto flex max-w-5xl flex-col gap-5 sm:flex-row sm:items-end sm:justify-between"
        >
          <div>
            <h1 class="text-6xl font-bold text-green-500 sm:text-5xl">
              My Days
            </h1>

            <p class="mt-2 text-sm text-black">
              Stay organized and get things done.
            </p>
          </div>

          <button
            @click="
              isAddTaskOpen = true;
              editingTask = null;
            "
            class="w-full rounded-lg bg-green-600 px-4 py-2 text-white transition hover:bg-green-700 sm:w-auto"
          >
            + Add Task
          </button>
        </div>
      </section>

      <!-- Statistics -->
      <section class="px-4 py-2 sm:px-6">
        <div class="mx-auto grid max-w-5xl grid-cols-1 gap-4 sm:grid-cols-3">
          <div class="rounded-xl border border-green-100 bg-green-50 p-5">
            <p>Total Tasks</p>
            <h1 class="mt-3 text-2xl font-semibold text-green-700">
              {{ tasks.length }}
            </h1>
          </div>

          <div class="rounded-xl border border-green-100 bg-green-50 p-5">
            <p>Active</p>
            <h1 class="mt-3 text-2xl font-semibold text-green-700">
              {{ tasks.filter((tasks) => !tasks.completed).length }}
            </h1>
          </div>

          <div class="rounded-xl border border-green-100 bg-green-50 p-5">
            <p>Completed</p>
            <h1 class="mt-3 text-2xl font-semibold text-green-700">
              {{ tasks.filter((tasks) => tasks.completed).length }}
            </h1>
          </div>
        </div>
      </section>

      <!-- Toolbar -->
      <section class="px-4 pt-2 pb-3 sm:px-6">
        <div class="mx-auto max-w-5xl">
          <div
            class="flex flex-col gap-3 border-b border-gray-200 pb-5 lg:flex-row lg:items-center lg:justify-between"
          >
            <!-- Search -->
            <div class="relative w-full lg:max-w-xs">
              <Search
                class="absolute left-3 top-1/2 h-5 w-5 -translate-y-1/2 text-gray-400"
              />

              <input
                v-model="searchQuery"
                type="text"
                name="task"
                placeholder="Search task..."
                class="w-full rounded-xl border border-gray-300 py-3 pl-10 pr-5 outline-none focus:border-green-500"
              />
            </div>

            <!-- Filters + Sort -->
            <div class="flex flex-col gap-3 sm:flex-row sm:items-center">
              <!-- Filters -->
              <div
                class="flex w-full items-center justify-center rounded-lg bg-gray-200 p-1 sm:w-auto"
              >
                <button
                  @click="activeFilter = 'all'"
                  :class="[
                    'rounded-md px-3 py-2 text-sm sm:px-4',
                    activeFilter === 'all'
                      ? 'bg-white shadow-sm text-green-600'
                      : 'text-black',
                  ]"
                >
                  All
                </button>

                <button
                  @click="activeFilter = 'active'"
                  :class="[
                    'rounded-md px-3 py-2 text-sm sm:px-4',
                    activeFilter === 'active'
                      ? 'bg-white shadow-sm text-green-600'
                      : 'text-black',
                  ]"
                >
                  Active
                </button>

                <button
                  @click="activeFilter = 'completed'"
                  :class="[
                    'rounded-md px-3 py-2 text-sm sm:px-4',
                    activeFilter === 'completed'
                      ? 'bg-white shadow-sm text-green-600'
                      : 'text-black',
                  ]"
                >
                  Completed
                </button>
              </div>

              <!-- Sort -->
              <div class="relative w-full sm:w-36">
                <button
                  @click="isSortOpen = !isSortOpen"
                  class="w-full rounded-lg border border-gray-300 px-4 py-2 text-black hover:border-green-600"
                >
                  {{ sortOrder === "newest" ? "Newest First" : "Oldest First" }}
                </button>

                <div
                  v-if="isSortOpen"
                  class="absolute right-0 top-full z-10 mt-2 w-full rounded-lg border border-gray-200 bg-white shadow-md"
                >
                  <button
                    v-if="sortOrder === 'newest'"
                    @click="
                      sortOrder = 'oldest';
                      isSortOpen = false;
                    "
                    class="w-full px-4 py-2 text-left text-gray-600 hover:bg-gray-100"
                  >
                    Oldest First
                  </button>

                  <button
                    v-if="sortOrder === 'oldest'"
                    @click="
                      sortOrder = 'newest';
                      isSortOpen = false;
                    "
                    class="w-full px-4 py-2 text-left text-gray-600 hover:bg-gray-100"
                  >
                    Newest First
                  </button>
                </div>
              </div>
            </div>
          </div>
        </div>
      </section>

      <!-- Task List -->
      <section class="px-4 pb-4 sm:px-6">
        <div class="mx-auto max-w-5xl">
          <TaskLists
            :tasks="filteredTasks"
            :totalTasks="tasks.length"
            @delete="deleteTask"
            @edit="openEditTask"
            @update="updateTaskStatus"
          />
        </div>
      </section>

      <!-- Add Task Modal -->
      <AddTask
        v-if="isAddTaskOpen"
        :task="editingTask"
        @add="addTask"
        @edit="editTask"
        @close="
          isAddTaskOpen = false;
          editingTask = null;
        "
      />
    </div>
  </div>
</template>
