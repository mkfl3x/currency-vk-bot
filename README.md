## Description
This is a simple VK bot for getting currency rates (USD, EUR) for RUB.<br>
It was created only for fun and play a bit with VK Java SDK and VK Callback.

## How to run it
1. At first you need to register community and set callback server. [Here](https://vk.com/dev/callback_api) you can read how.<br>
   You should use following address: **https://[your_server]/callback**<br> as endpoint
2. Then you need set values in **config.properties** file
   * bot.confirmation - code for confirm your callback server
   * bot.id - id of your community (because bot is part of it)
   * bot.token - you can generate it with guide from [here](https://vk.com/dev/implicit_flow_group)
   * update.time - interval between cources updates (bot caches them)
3. Run it:
   * With `main()` method
   * With gradle: `gradle bootRun` (it requires Gradle 7.+)
4. PROFIT!!!