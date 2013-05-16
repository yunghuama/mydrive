/**问题对象**/
function Question(num,id,question,answer_a,answer_b,answer_c,answer_d,answer,img,vedio) {
	this.num = num;
	this.id = id;
	this.question = question;
	this.answer_a = answer_a;
	this.answer_b = answer_b;
	this.answer_c = answer_c;
	this.answer_d = answer_d;
	this.answer = answer;
	this.img = img;
	this.vedio = vedio;
}

/**答案对象**/
function Answer(num,questionid,answer){
	this.id = id;
	this.answer = answer;
}