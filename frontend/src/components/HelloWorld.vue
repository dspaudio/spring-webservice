<template>
  <div class="hello">
    <h1>Hello {{ msg }}</h1>
    <ul v-bind:key="key" v-for="(item, key) in pageable.content " >
      <li>
        <span>{{ item.id }}</span> |
        <span>{{ item.boardCategory.boardCategoryName }}</span> |
        <span>{{ item.title }}</span> |
        <span>{{ item.filesCount }}</span> |
        <span>{{ item.createdBy.userName }}</span> |
        <span>{{ item.createdDate }}</span> |
        <a>상세보기</a> |
        <a>삭제하기</a>
      </li>
    </ul>
    <span :class="{ off : pageable.first }"> <a>-toFirst-</a> <a>prev</a> </span>
    {{ pageable.number +1 }} / {{ pageable.totalPages }}
    <span :class="{ off : pageable.last }"><a>next</a> <a>-toLast-</a> </span>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js sssApp',
      pageable: null
    }
  },
  mounted () {
    this.load()
  },
  methods: {
    load () {
      axios.get('/api/post/list').then((resp) => {
        this.pageable = resp.data
      })
    }
  }
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h1, h2 {
  font-weight: normal;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
span.off a {
  color: #bbbbbb;
}
</style>
