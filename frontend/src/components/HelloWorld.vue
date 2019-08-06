<template>
  <div id="listPageable" class="hello">
    <ul>
      <li v-bind:key="key" v-for="(item, key) in pageable.content ">
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
  data () {
    return {
      pageable: []
    }
  },
  created () {
    axios.get('/api/post/list').then((response) => {
      this.pageable = response.data
      console.log(this.pageable)
    })
  }
}

// var listPageable = new Vue({
//   el: '#listPageable',
//   data: {
//     message: '안녕하세요 Vue!'
//   }
// })

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
  margin: 0 10px;
}
span.off a {
  color: #bbbbbb;
}
</style>
