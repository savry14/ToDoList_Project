<script setup>
import { Search } from "lucide-vue-next";
import { ref } from "vue";

const activeFilter = ref("all");
const isSortOpen = ref(false);
const sortOrder = ref("newest");
</script>

<template>
  <div class="w-full h-screen bg-blue-200">
    <section class="px-6 pt-20 pb-10">
      <div class="mx-auto max-w-7xl flex items-end justify-between">
        <div>
          <p class="text-sm font-medium tracking-wide text-green-600">
            YOUR WORKSPACE
          </p>
          <h1 class="mt-1 text-5xl font-semibold text-white">My Tasks</h1>
          <p class="mt-3 text-sm text-black">
            Stay organized and get things done.
          </p>
        </div>
        <div>
          <button class="bg-green-600 px-4 py-2 text-white rounded-lg">
            + Add Task
          </button>
        </div>
      </div>
    </section>

    <section class="px-6 py-10">
      <div class="mx-auto max-w-7xl flex justify-between gap-8">
        <div class="w-full rounded-xl bg-green-200 p-6">
          <p>Total Tasks</p>
          <h1 class="mt-4 text-2xl font-semibold">0</h1>
        </div>
        <div class="w-full rounded-xl bg-green-200 p-6">
          <p>Active</p>
          <h1 class="mt-4 text-2xl font-semibold">0</h1>
        </div>
        <div class="w-full rounded-xl bg-green-200 p-6">
          <p>Completed</p>
          <h1 class="mt-4 text-2xl font-semibold">0</h1>
        </div>
      </div>
    </section>

    <section class="px-6 pt-8 pb-10">
      <div class="mx-auto max-w-7xl">
        <div
          class="w-full rounded-xl bg-white p-6 flex items-center justify-between gap-6"
        >
          <!-- search -->
          <div class="w-100 relative">
            <Search
              class="absolute left-3 top-1/2 -translate-y-1/2 w-5 h-5 text-gray-400"
            />
            <input
              type="search"
              name="task"
              id=""
              placeholder="Search task..."
              class="w-full rounded-xl border border-gray-300 pl-10 pr-4 px-4 py-3 outline-none focus:border-green-500"
            />
          </div>

          <div class="flex items-center gap-4">
            <div class="flex items-center rounded-lg bg-gray-200 p-1">
              <!-- All -->
              <button
                @click="activeFilter = 'all'"
                :class="[
                  'rounded-md px-4 py-2 ',
                  activeFilter === 'all'
                    ? ' bg-white shadow-sm text-green-600'
                    : 'text-black',
                ]"
              >
                All
              </button>

              <!-- action -->
              <button
                @click="activeFilter = 'active'"
                :class="[
                  'rounded-md px-4 py-2 ',
                  activeFilter === 'active'
                    ? 'bg-white shadow-sm text-green-600'
                    : 'text-black',
                ]"
              >
                Active
              </button>

              <!-- Finished -->
              <button
                @click="activeFilter = 'completed'"
                :class="[
                  'rounded-md px-4 py-2',
                  activeFilter === 'completed'
                    ? 'bg-white shadow-sm text-green-600'
                    : 'text-black',
                ]"
              >
                Completed
              </button>
            </div>
            <div class="relative">
              <button
                @click="isSortOpen = !isSortOpen"
                class="w-36 px-4 py-2 text-black rounded-lg border border-gray-200 hover:border-green-600"
              >
                {{ sortOrder === "newest" ? "Newest First" : "Oldest First" }}
              </button>

              <div
                v-if="isSortOpen"
                class="absolute right-0 top-full mt-2 w-full rounded-lg border border-gray-200 bg-white shadow-md"
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
                  class="w-full px-4 py-2 text-left text-gray-600 bg-white hover:bg-gray-100"
                >
                  Newest First
                </button>
              </div>
            </div>
          </div>
        </div>
      </div>
    </section>
  </div>
</template>
