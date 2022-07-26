# Gabriela Córdova 
## Social Media Posterr

Attached to this solution there is a postman collection with all endpoints implemented with query examples.
Also, there is a data.sql file which contains the database query to create and populate tables used is this project.

ATTENTION: 
In real life example adding a post would be by endpoint (/post/personal) or (post/interact) so everytime a post is made the counter post in users table would be incremented.
By adding the post with database query, make sure to update the counter posts in user table so data stays accurate.


### <b> Critique </b>

Improvements: 

Thinking about scaling, probably would be better to have separate API's for the different pages and actions. Home-Page and Profile-Page each have its own API and also posting content should be a separate API.
Design a NoSQL database solution if Posterr scalated fast, a NoSQL database would be faster and provide better dynamic.
Implement more features for the social media, thinking about how many features nowadays social medias have, only posting text is kind of too little.
Even twitter today have features of posting videos, photos, gifs, commenting on publications... so many features that could be implemented. 
Behaviour testing rather than just unit testing the solution.
Desing a MVP and get to the market, so the next version would be improved by people's opinion on the solution.

If this project were to grow and have many users and posts, which parts do you think would fail first? 
Probably database... not that would actually fail, but would definitely take a long time to respond. 

In a real-life situation, what steps would you take to scale this product? What other types of technology and infrastructure might you need to use?

In a real life situation, more tha just the time for execution, I believe there would be a bigger team thinking about the solution, so software architectures would be involved on the best solution to scalate the solution.
A flowchart with all steps of the social media would be implemented. 
But basicaly the steps to get it to production would be:

Developing -> Testing locally -> deploy do develop environment -> Developer testing with QA tests case:
In case every thing works fine: QA testing in dev -> deploy to homologation environment ->  
In case every thing works -> deploy to production
In any scenario if it does not work, goes back to developer to fix the errors.
