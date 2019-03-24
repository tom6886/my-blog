import Vue from 'vue'
import {Button, Card, Col, Loading, Menu, MenuItem, Pagination, Row, Submenu, Dialog} from 'element-ui'
/*按需引入*/
export default () => {
  Vue.use(Button)
  Vue.use(Loading)
  Vue.use(Menu)
  Vue.use(MenuItem)
  Vue.use(Submenu)
  Vue.use(Row)
  Vue.use(Col)
  Vue.use(Card)
  Vue.use(Pagination)
  Vue.use(Dialog)
}
