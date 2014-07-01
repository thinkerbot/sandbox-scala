object sandbox {

  // if case is not present then we are passing a function so all these shorter versions are possible
  
/*
I'm going to do my best to parrot things I picked up from the scala course...
What you've done here by declaring functions with `{}` works but I don't think
it is the intended syntax.  Odersky presented it like this:

`()` is how you call a function, ie they wrap parameters just like you would expect.
If one parameter is a function literal then you get the a form like this:
*/

  val nums = List(0,1,0)                          //> nums  : List[Int] = List(0, 1, 0)
  nums.map((x) => {x.toString})                   //> res0: List[String] = List(0, 1, 0)

/*
This can be simplified in a bunch of ways.
*/

  nums.map(x => x.toString) // version a - remove {}
                                                  //> res1: List[String] = List(0, 1, 0)
  nums.map({_.toString})    // version b - remove arg declaration
                                                  //> res2: List[String] = List(0, 1, 0)
  nums.map(_.toString)      // version c - remove arg declaration and {}
                                                  //> res3: List[String] = List(0, 1, 0)
  nums.map {_.toString}     // version d - remove arg declaration and ()
                                                  //> res4: List[String] = List(0, 1, 0)

/*
The important thing to note is that in all versions these are declarations of a function
literal; in the last case parens can be removed because parens can be removed sometimes (or at
least that's how I read it).  However, for a particular type of function (match) scala provides
braces as a special declaration syntax.
*/

  nums.map((x) => {x match {
    case 0 => "zero"
    case 1 => "one"
  }})                                             //> res5: List[String] = List(zero, one, zero)
  nums.map({             // version x - use special match declaration
    case 0 => "zero"
    case 1 => "one"
  })                                              //> res6: List[String] = List(zero, one, zero)
  nums.map {             // version y - use special match declaration and remove ()
    case 0 => "zero"
    case 1 => "one"
  }                                               //> res7: List[String] = List(zero, one, zero)

/*
So in sum I think your use of `{}` (maybe to remind us of ruby blocks?) is valid but doesn't
follow the intended use of `{}`.  I can't say for sure this is THE rule, but it was the
way Odersky presented it and it makes pretty good sense to me:

* `()` everywhere
* `{}` if you want a shorthand match function

Like this:
*/
  nums.map( _.toString )                          //> res8: List[String] = List(0, 1, 0)
  nums.map {
    case 0 => "zero"
    case 1 => "one"
  }                                               //> res9: List[String] = List(zero, one, zero)


  // cannot remove match declaration and {} -- highlights that {} is a special match syntax
  //nums.map(
  //  case 0 => "zero"
  //  case 1 => "one"
  //)
  
    nums.map(x => x match {
    case 0 => "zero"
    case 1 => "one"
  })                                              //> res10: List[String] = List(zero, one, zero)
  
  nums.map {
    case 0 => "zero"
    case 1 => "zero"
  }                                               //> res11: List[String] = List(zero, zero, zero)

  def case_with_case(x: Int): String = x match {
    case 0 => "zero"
    case 1 => "one"
  }                                               //> case_with_case: (x: Int)String
  nums.map(case_with_case)                        //> res12: List[String] = List(zero, one, zero)
  
}