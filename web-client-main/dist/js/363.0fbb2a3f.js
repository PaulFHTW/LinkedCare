"use strict";(self["webpackChunklinked_care_web_client"]=self["webpackChunklinked_care_web_client"]||[]).push([[363],{1363:function(e,a,n){n.r(a),n.d(a,{default:function(){return g}});var t=n(641),r=n(3751),o=n(33);const i={class:"container"},s={class:"row"},l={class:"login col-6 card"},u={class:"mb-3"},c={class:"mb-3"},d={key:0};function p(e,a,n,p,h,m){return(0,t.uX)(),(0,t.CE)("div",i,[(0,t.Lk)("div",s,[a[6]||(a[6]=(0,t.Lk)("h2",null,"Login",-1)),a[7]||(a[7]=(0,t.Lk)("div",{class:"col-3"},null,-1)),(0,t.Lk)("div",l,[(0,t.Lk)("form",{onSubmit:a[2]||(a[2]=(0,r.D$)(((...e)=>m.validateForm&&m.validateForm(...e)),["prevent"]))},[(0,t.Lk)("div",u,[a[3]||(a[3]=(0,t.Lk)("label",{for:"inputEmail",class:"form-label"},"Email-Adresse",-1)),(0,t.bo)((0,t.Lk)("input",{type:"email","onUpdate:modelValue":a[0]||(a[0]=e=>h.loginData.email=e),class:"form-control",id:"inputEmail",required:""},null,512),[[r.Jo,h.loginData.email]])]),(0,t.Lk)("div",c,[a[4]||(a[4]=(0,t.Lk)("label",{for:"inputPassword",class:"form-label"},"Passwort",-1)),(0,t.bo)((0,t.Lk)("input",{type:"password","onUpdate:modelValue":a[1]||(a[1]=e=>h.loginData.password=e),class:"form-control",id:"inputPassword",required:""},null,512),[[r.Jo,h.loginData.password]])]),a[5]||(a[5]=(0,t.Lk)("button",{type:"submit",class:"btn btn-secondary mb-3"},"Einloggen",-1)),h.errorMsg.length?((0,t.uX)(),(0,t.CE)("b",d,[(0,t.Lk)("ul",null,[((0,t.uX)(!0),(0,t.CE)(t.FK,null,(0,t.pI)(h.errorMsg,((e,a)=>((0,t.uX)(),(0,t.CE)("li",{key:a},(0,o.v_)(e),1)))),128))])])):(0,t.Q3)("",!0)],32)])])])}n(4114),n(4603),n(7566),n(8721);var h={setup(){},data(){return{loginData:{email:"",password:""},errorMsg:[]}},methods:{validateForm(){this.errorMsg=[],""!=this.loginData.email&&null!=this.loginData.email||this.errorMsg.push("Bitte geben Sie Ihre Email-Adresse an!"),""!=this.loginData.password&&null!=this.loginData.password||this.errorMsg.push("Bitte geben Sie Ihr Passwort ein!"),this.errorMsg.length||this.loginUser()},loginUser(){const e=new Headers;e.append("Content-Type","application/x-www-form-urlencoded");const a=new URLSearchParams;a.append("grant_type","password"),a.append("client_id","JavaBackend"),a.append("username",this.loginData.email),a.append("password",this.loginData.password);const n={method:"POST",headers:e,body:a,redirect:"follow"};let t,r;fetch("http://172.29.16.61:8080/realms/data-facade-app/protocol/openid-connect/token",n).then((e=>e.json())).then((e=>{r=e})).then((()=>{t=r["access_token"]})).then((()=>{document.cookie=`${t};SameSite=None Secure`})).catch((e=>console.error(e)))}}},m=n(6262);const f=(0,m.A)(h,[["render",p],["__scopeId","data-v-ab06f5c8"]]);var g=f},655:function(e,a,n){var t=n(6955),r=String;e.exports=function(e){if("Symbol"===t(e))throw new TypeError("Cannot convert a Symbol value to a string");return r(e)}},2812:function(e){var a=TypeError;e.exports=function(e,n){if(e<n)throw new a("Not enough arguments");return e}},4603:function(e,a,n){var t=n(6840),r=n(9504),o=n(655),i=n(2812),s=URLSearchParams,l=s.prototype,u=r(l.append),c=r(l["delete"]),d=r(l.forEach),p=r([].push),h=new s("a=1&a=2&b=3");h["delete"]("a",1),h["delete"]("b",void 0),h+""!=="a=2"&&t(l,"delete",(function(e){var a=arguments.length,n=a<2?void 0:arguments[1];if(a&&void 0===n)return c(this,e);var t=[];d(this,(function(e,a){p(t,{key:a,value:e})})),i(a,1);var r,s=o(e),l=o(n),h=0,m=0,f=!1,g=t.length;while(h<g)r=t[h++],f||r.key===s?(f=!0,c(this,r.key)):m++;while(m<g)r=t[m++],r.key===s&&r.value===l||u(this,r.key,r.value)}),{enumerable:!0,unsafe:!0})},7566:function(e,a,n){var t=n(6840),r=n(9504),o=n(655),i=n(2812),s=URLSearchParams,l=s.prototype,u=r(l.getAll),c=r(l.has),d=new s("a=1");!d.has("a",2)&&d.has("a",void 0)||t(l,"has",(function(e){var a=arguments.length,n=a<2?void 0:arguments[1];if(a&&void 0===n)return c(this,e);var t=u(this,e);i(a,1);var r=o(n),s=0;while(s<t.length)if(t[s++]===r)return!0;return!1}),{enumerable:!0,unsafe:!0})},8721:function(e,a,n){var t=n(3724),r=n(9504),o=n(2106),i=URLSearchParams.prototype,s=r(i.forEach);t&&!("size"in i)&&o(i,"size",{get:function(){var e=0;return s(this,(function(){e++})),e},configurable:!0,enumerable:!0})}}]);
//# sourceMappingURL=363.0fbb2a3f.js.map