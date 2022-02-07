# Questions

> As stated there may be bugs in the code, try to find and fix them.

It's hard for me to say it in this way. Only simple base cases are covered by the initial implementation.

> The maximum amount per day and vehicle is 60 SEK.

Is completely missed in the original code. **Well known bug**

> A vehicle that passes several tolling stations within 60 minutes is only taxed once. The amount that must be paid is the highest one.

The initial solution has an incorrect implementation for this case. **Well known bug**

The correct approach might be: 
1. at every date we have a choice: 
   1. pick the current element and skip the next 60 minutes
   2. skip the current element
2. maximize the sum
3. apply dynamic programming (memoization) if 2^n -> n optimization is required, where n = dates count

The requirements contain **no** examples. Adding a few examples could dramatically accelerate the time needed to understand the problem.
