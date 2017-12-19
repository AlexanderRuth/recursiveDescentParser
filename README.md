My progress on designing a recursive descent parser for mathematical equations

  \<expr\> :== \<term\> + \<expr\> \n
  \<term\> :== \<factor\> * \<term\> \n
  \<factor\> :== (\<expr\>) | [0 - 9]
