<template>
  <div class="hello">
    <h1>Hello {{ msg }}</h1>
    <ul v-if="list.length > 0" v-bind:key="key" v-for="(item, key) in list">
      <li>
        <div>{{ key +" : "+ item.name }}</div>
        <div>{{ key +" : "+ item.age }}</div>
        <div>{{ key +" : "+ item.sex }}</div>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'HelloWorld',
  data () {
    return {
      msg: 'Welcome to Your Vue.js App',
      list: []
    }
  },
  mounted () {
    this.load()
  },
  methods: {
    load () {
      axios.get('/api/hello').then((resp) => {
        console.log(resp.data)
        this.msg = resp.data
      })
      axios.get('/api/list').then((resp) => {
        this.list = resp.data
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
a {
  color: #42b983;
}
</style>
