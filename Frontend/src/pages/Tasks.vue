<script setup>
import TaskLists from "../components/TaskLists.vue";
import { Search } from "lucide-vue-next";
import { ref, computed } from "vue";

const activeFilter = ref("all");
const isSortOpen = ref(false);
const sortOrder = ref("newest");
const isAddTaskOpen = ref(false);
const searchQuery = ref("");
const editingTask = ref(null);

const tasks = ref([
  {
    id: 1,
    title: "Finish homepage UI",
    completed: false,
    createdAt: new Date("2026-09-28T10:00:00"),
    date: "Today",
  },
  {
    id: 2,
    title: "Study JavaScript",
    completed: true,
    createdAt: new Date("2026-09-27T10:00:00"),
    date: "Yesterday",
  },
]);

function addTask(title) {
  tasks.value.push({
    id: Date.now(),
    title: title,
    completed: false,
    createdAt: new Date(),
    date: "Today",
  });
  isAddTaskOpen.value = false;
}

function editTask(task) {
  editingTask.value = task;
  isAddTaskOpen.value = true;
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
  <div class="min-h-screen w-full bg-slate-300">
    <!-- Header -->
    <section class="px-4 pt-10 pb-6 sm:px-6 sm:pt-12">
      <div
        class="mx-auto flex max-w-7xl flex-col gap-5 sm:flex-row sm:items-end sm:justify-between"
      >
        <div>
          <h1 class="text-6xl font-bold text-green-500 sm:text-5xl">My Days</h1>

          <p class="mt-2 text-sm text-black">
            Stay organized and get things done.
          </p>
        </div>

        <button
          @click="isAddTaskOpen = true;  editingTask = null;"
          class="w-full rounded-lg bg-green-600 px-4 py-2 text-white transition hover:bg-green-700 sm:w-auto"
        >
          + Add Task
        </button>
      </div>
    </section>

    <!-- Statistics -->
    <section class="px-4 py-4 sm:px-6">
      <div class="mx-auto grid max-w-7xl grid-cols-1 gap-4 sm:grid-cols-3">
        <div class="rounded-xl bg-green-200 p-5">
          <p>Total Tasks</p>
          <h1 class="mt-3 text-2xl font-semibold">{{ tasks.length }}</h1>
        </div>

        <div class="rounded-xl bg-green-200 p-5">
          <p>Active</p>
          <h1 class="mt-3 text-2xl font-semibold">
            {{ tasks.filter((tasks) => !tasks.completed).length }}
          </h1>
        </div>

        <div class="rounded-xl bg-green-200 p-5">
          <p>Completed</p>
          <h1 class="mt-3 text-2xl font-semibold">
            {{ tasks.filter((tasks) => tasks.completed).length }}
          </h1>
        </div>
      </div>
    </section>

    <!-- Toolbar -->
    <section class="px-4 pt-4 pb-6 sm:px-6">
      <div class="mx-auto max-w-7xl">
        <div
          class="flex flex-col gap-4 rounded-xl bg-white p-4 sm:p-5 lg:flex-row lg:items-center lg:justify-between"
        >
          <!-- Search -->
          <div class="relative w-full lg:max-w-sm">
            <Search
              class="absolute left-3 top-1/2 h-5 w-5 -translate-y-1/2 text-gray-400"
            />

            <input
              v-model="searchQuery"
              type="text"
              name="task"
              placeholder="Search task..."
              class="w-full rounded-xl border border-gray-300 py-3 pl-10 pr-5. outline-none focus:border-green-500"
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
    <section class="px-4 pb-10 sm:px-6">
      <div class="mx-auto max-w-7xl">
        <TaskLists
          :tasks="filteredTasks"
          :totalTasks="tasks.length"
          @delete="deleteTask"
          @edit="editTask"
        />
      </div>
    </section>

    <!-- Add Task Modal -->
    <AddTaskModal
      v-if="isAddTaskOpen"
      :task="editingTask"
      @add="addTask"
      @close="isAddTaskOpen = false;  editingTask = null;"
    />
  </div>
</template>
