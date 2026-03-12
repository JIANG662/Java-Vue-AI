<script setup lang="ts">
import {nextTick, onMounted, ref} from "vue";
import {ElMessage} from "element-plus";
import axios from "axios";
import MarkdownIt from 'markdown-it'
import hljs from 'highlight.js'
// 自定义高亮规则
import 'highlight.js/styles/panda-syntax-light.css'
import {addMsg} from "@/api/dao.ts";
const initHighlight = () => {
  hljs.configure({
    languages: ['javascript', 'python', 'java'],
    ignoreUnescapedHTML: true
  })
}
const useMarkdownParser = () => {
  const md = ref(new MarkdownIt({
    html: true,
    highlight: (code, lang) => {
      return hljs.highlightAuto(code).value
    }
  }))
  const parsedContent = ref('')
  const parseContent = (content:string) => {
    return md.value.render(content)
  }
  onMounted(() => {
    hljs.highlightAll() // 初始化代码高亮
  })
  return { parsedContent, parseContent }
}
// 在组合式函数中调用
onMounted(() => {
  initHighlight()
})
// 组件实现
const { parsedContent, parseContent } = useMarkdownParser()
/* 定义发送的内容 */
const content = ref('')

/* 定义表情标题列表 */
const imgList = ref(['../assets/img/chat_image2.webp', '../assets/img/chat_image4.webp'])

const getImg = () => {
  /* Math.floor小数取整   Math.random()随机数[0,1) */
  let index = Math.floor(Math.random() * imgList.value.length)
  /* 将本地资源地址转化为在线访问 */
  return new URL(`${imgList.value[index]}`, import.meta.url).href;
};

/* new URL(`../assets/chat_image2.webp`, import.meta.url).href 将静态图片的路径转成在线地址 */
const imgShow = ref(new URL(`../assets/img/chat_image2.webp`, import.meta.url).href)

/* 定时器 每隔5秒调用一次getImg()方法获得一个在线图片链接 */
setInterval(() => {
  imgShow.value = getImg()
}, 7000)

/* 消息列表 */
const messageList = ref([
  {
    uid:0,
    username: '小白',
    avatar: '',
    message: '我是机器人小白，很高兴为您服务😍'
  },
  {
    uid:1,
    username: 'white',
    avatar: 'https://img2.baidu.com/it/u=245010679,4205024009&fm=253&fmt=auto&app=120&f=JPEG?w=768&h=1280',
    message: '心情不好，给我聊聊天吧~'
  },
])

/* 表情列表 */
const emojiList = ref(['😀', '😂', '😍', '😋', '🤔', '🫣', '🤐', '😴', '😎', '😱', '😡'])

/* 追加表情 */
const addEmoji = (emoji: any) => {
  content.value += emoji
}
const scroll =ref<any>()/*获取滚动条组件对象*/
const items = ref<any>()/*获取内部div子元素节点对象*/
/* 发送消息 */
const sendMessage = () => {
  if (content.value) {
    /* 组装消息 */
    const obj = {
      uid: 1,
      username: 'white',
      avatar: 'https://img2.baidu.com/it/u=245010679,4205024009&fm=253&fmt=auto&app=120&f=JPEG?w=768&h=1280',
      message: content.value
    }
    //存储用户发送的消息
    addMsg({
      ...obj,
      userId: obj.uid
    }).then((res:any)=>{
      console.log(res.data.msg)
    })
    /* 先往消息列表添加此消息，再向后端服务器发送请求，存储此消息 */
    messageList.value.push(obj)
    content.value = ''
//获取流式数据
    const eventSource = new EventSource('http://localhost:9090/chat/ai/generateStream?message='+obj.message)
        messageList.value.push({
          uid: 0,
          username: 'nobody',
          avatar: '',
          message: ''
        })
    //接收后台服务器返回的数据 一条条返回
    eventSource.onmessage = (event:any)=>{
      //接收流式数据 把返回的json转换为js对象
      let msg = JSON.parse(event.data).result.output.text
      if(msg == null) return
      messageList.value[messageList.value.length - 1].message += msg  /*获取最后一条消息 并追加（+=）新消息 实现一个字一个字拼接*/
      //自动滚动到底部
      //dom完成加载之后再触发函数内容 即页面消息加载完成之后再获取高度
      nextTick(()=>{
        /*items.value.scrollHeight获取当前div的高度*/
        /*setScrollTop滚动条与顶端的距离*/
        scroll.value.setScrollTop(items.value.scrollHeight)
      })
      //存储ai回复的消息
      //判断消息是否结束
      let flag = JSON.parse(event.data).result.metadata.finishReason
      if(flag == 'STOP'){
        addMsg({
          ...messageList.value[messageList.value.length - 1],
          userId:obj.uid
        }).then((res:any)=>{
          console.log(res.data.msg)
        })
      }
    }
    eventSource.onerror = (error:any)=>{
      eventSource.close()
    }
    //发送请求
    // axios({
    //   url:'http://localhost:9090/chat/ai/generate?message=' + obj.message,
    //   method:'get',
    //
    // }).then((res:any)=>{
    //   //res后台服务器返回的消息
    //   let msg = res.data.generation
    //   //ai返回的消息封装到消息数组中
    //   messageList.value.push({
    //     uid: 0,
    //     username: 'white',
    //     avatar: '',
    //     message: msg
    //   })
    //   //清空自己发的消息
    //   content.value = ''
    // })
  } else {
    /* 提示用户先写问题 */
    ElMessage.error('先填写点内容在发送吧！')
  }
}
</script>

<template>
  <div class="chat">
    <div class="chat-header">
      <el-scrollbar height="100%" style="padding: 0 15px" ref="scroll">
        <div ref="items"><!--和定义的ref名称保持一致 保证被获取到-->


          <!--   标题   -->
          <div class="bg-out">
            <div class="bq-bg">
              <div>
                <img style="margin-top: 5px" width="105px" :src="imgShow">
              </div>
            </div>
            <div style="font-size: 18px;font-weight: 600">我是小白很高兴见到您！</div>
          </div>
          <div class="chat-out" v-for="(item,index) in messageList" :key="index">
            <!--     左边聊天气泡       -->
            <div class="chat-left" v-if="item.uid == 0">
              <div>
                <img width="37px" height="37px" style="border-radius: 5px"
                     src="https://www.96035.com/wp-content/uploads/2023/05/673982698.png">
              </div>
              <div style="margin-left: 6px">
                <div>{{ item.username }}</div>
                <div class="left-msg">
                  <!--    markdown插件    -->
                  <!--  <MessageToMarkdown :message="item.message" />-->
                  <!--                <div>{{item.message}}</div>-->
                  <div class="markdown-viewer markdown-body" v-html="parseContent(item.message)"></div>
                </div>
              </div>
            </div>
            <!--    右侧聊天气泡        -->
            <div class="chat-right" v-else>

              <div style="margin-right: 6px;display: flex;flex-direction: column;">
                <div style="align-self: flex-end;">{{ item.username }}</div>
                <div class="right-msg">
                  {{ item.message }}
                </div>
              </div>
              <div>
                <img width="37px" height="37px" style="border-radius: 5px" :src="item.avatar">
              </div>
            </div>
          </div>
        </div>
      </el-scrollbar>

    </div>

    <div class="chat-bottom">
      <div class="bottom-left">
        <div>
          <el-input size="large" v-model="content" @keyup.enter="sendMessage" style="width: 55vw" placeholder="Ask me anything......"/>
        </div>
        <el-popover
            placement="bottom"
            :width="200"
            trigger="click"
        >
          <div style="display: flex;flex-wrap: wrap">
            <div style="margin: 3px;cursor: pointer" @click="addEmoji(item)" v-for="(item,index) in emojiList"
                 :key="index">{{ item }}
            </div>
          </div>

          <template #reference>
            <div class="iconfont icon-biaoqing"
                 style="font-size: x-large;color: gray;margin-left: 30px;cursor: pointer"></div>
          </template>
        </el-popover>
      </div>
      <div class="send-bt" @click="sendMessage">
        <div class="iconfont icon-fasong"></div>
      </div>
    </div>

  </div>
</template>

<style scoped>
.chat {
  height: 100%;
}

.chat-bottom {
  height: 75px;
  border-top: 1px solid #e2e2e2;
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 0 15px;
  box-sizing: border-box;
}

.chat-header {
  height: calc(100% - 75px);
}

.bottom-left {
  display: flex;
  align-items: center;
}

.icon-7biaoqing-1 {
  font-size: 30px;
  color: #999999;
  margin-left: 20px;
  /* 鼠标变小手（交互样式） */
  cursor: pointer;
}

.send-bt {
  width: 50px;
  height: 36px;
  border-radius: 6px;
  background-color: #409EFF;
  display: flex;
  justify-content: center;
  align-items: center;
  color: white;
  cursor: pointer;
}

.icon-send-s {
  font-size: 20px;
}

.bq-bg {
  /* 背景图 */
  background: url("../assets/img/image1-2.webp");
  /* 背景大小 cover覆盖 */
  background-size: cover;
  height: 90px;
  width: 90px;
  display: flex;
  justify-content: center;
  align-items: center;
}

.bg-out {
  display: flex;
  justify-content: center;
  align-items: center;
}

.chat-left {
  display: flex;
  margin: 20px 0;
}

.left-msg {
  background-color: #EFEFEF;
  padding: 10px;
  margin-top: 8px;
  border-top-right-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
  max-width: 865px;
  /* 英文自动换行 */
  word-wrap: break-word;
  overflow-wrap: break-word;
  word-break: normal;
}

.chat-right {
  display: flex;
  align-self: flex-end;
  margin: 20px 0;
}

.right-msg {
  background-color: #409EFF;
  padding: 10px;
  margin-top: 8px;
  border-top-left-radius: 10px;
  border-bottom-right-radius: 10px;
  border-bottom-left-radius: 10px;
  max-width: 865px;
  /* 英文自动换行 */
  word-wrap: break-word;
  overflow-wrap: break-word;
  word-break: normal;
  color: white;
}

.chat-out {
  display: flex;
  flex-direction: column;
}
</style>
<style>
@import 'github-markdown-css';
.markdown-body{
  background-color: #EFEFEF;
}
</style>